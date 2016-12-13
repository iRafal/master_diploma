using System;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Http;
using AutoMapper;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.DTORepositories;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemAPI.NeuralNetwork;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;

namespace NeuralNetworkSystemAPI.Controllers
{
    public class NeuralNetworkController : NeuralNetworkBaseController
    {
        private readonly NeuralNetworkHelper _neuralNetworkHelper;
        private readonly DataBaseSuggestionRepository _suggestionRepository;

        public NeuralNetworkController()
        {
            _neuralNetworkHelper = new NeuralNetworkHelper();
            _suggestionRepository = new DataBaseSuggestionRepository();
        }

        public NeuralNetworkResponse GetForecast([FromUri]DieseaseRequestModel requestModel)
        {
            var neuralNetwork = GetDiseaseNeuralNetwork();
            var input = _neuralNetworkHelper.MapToInput(requestModel, neuralNetwork, true);

            neuralNetwork.Calculate(input);
            var output = _neuralNetworkHelper.GetNetwrokOutput(neuralNetwork.GetOutputLayer());
            var disease = (DiseasesStatusEnum)Enum.ToObject(typeof(DiseasesStatusEnum), output);

            var suggestionList = _suggestionRepository.GetAllSuggestions(disease);


            var groupRisk = RiskStatusEnum.Hight;

            if (disease != DiseasesStatusEnum.Normal)
            {
                var networkType = (NetworkTypeEnum)Enum.ToObject(typeof(NetworkTypeEnum), output);
                neuralNetwork = GetGroupRiskNeuralNetwork(networkType);

                neuralNetwork.Calculate(input);
                output = _neuralNetworkHelper.GetNetwrokOutput(neuralNetwork.GetOutputLayer());
                groupRisk = (RiskStatusEnum)Enum.ToObject(typeof(RiskStatusEnum), output);
            }

            var diseaseResponse = new DiseaseResponseObjectModel()
            {
                Disease = new Disease
                {
                    Status = disease,
                    Name = disease.ToString()
                },
                GroupRisk = new GroupRisk
                {
                    GroupRiskType = groupRisk,
                    Name = groupRisk.ToString()
                },
                Suggestions = suggestionList
            };

            var neuralResponse = new NeuralNetworkResponse();
            neuralResponse.Forecasts.Add(diseaseResponse);

            return neuralResponse;
        }

        public HttpResponse PostSample(DiseaseLearningSampleModel learningSample)
        {
            var dbLearningSample = Mapper.Map<DiseaseLearningSampleModel, DiseaseMonitoringSample>(learningSample);

            dbLearningSample.Disease = new Disease
            {
                Status = learningSample.DiseaseStatus,
                Name = learningSample.DiseaseStatus.ToString()
            };

            dbLearningSample.GroupRisk = new GroupRisk
            {
                GroupRiskType = learningSample.GroupRiskStatus,
                Name = learningSample.GroupRiskStatus.ToString()
            };

            var repo = new DataBaseDiseasesLearningSamplesRepository
            {
                NetworkType = NetworkTypeEnum.DiseaseNeuralNetwork
            };

            repo.SaveNewSample(dbLearningSample);

            var diseaseNeuralNetwork = GetDiseaseNeuralNetwork();
            diseaseNeuralNetwork.LearnNetwork();

            var groupRiskNeuralNetwork = GetGroupRiskNeuralNetwork((NetworkTypeEnum)Enum.ToObject(typeof(NetworkTypeEnum), (int)learningSample.DiseaseStatus));
            groupRiskNeuralNetwork.LearnNetwork();

            return null;
        }

        public void LearnNetwork()
        {
            var neuralNetworkTypes = Enum.GetValues(typeof(NetworkTypeEnum)).Cast<NetworkTypeEnum>();

            foreach (var networkType in neuralNetworkTypes)
            {
                if (networkType == NetworkTypeEnum.DiseaseNeuralNetwork)
                {
                    var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetworkSystemBLL.NeuralNetwork>();

                    var neuralNetwork = neuralNetworkBuilder
                        .WithLayerType(new NeuralLayer())
                        .WithActivationFunctionType(new SigmoidalActivationFunction())
                        .WithInductedFunctionType(new InductedLocalFieldFunction())
                        .WithLearningFunctionsType(new SigmoidalLearningFunctions())
                        .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                        .WithInputCount(int.Parse(ConfigurationManager.AppSettings["DiseaseInputCount"]))
                        .WithHiddenLayersCount(int.Parse(ConfigurationManager.AppSettings["DiseaseHiddenCount"]))
                        .WithHiddenLayersLength(int.Parse(ConfigurationManager.AppSettings["DiseaseLenghtCount"]))
                        .WithOutputCount(int.Parse(ConfigurationManager.AppSettings["DiseaseOutputCount"]))
                        .WithErrorThreshold(double.Parse(ConfigurationManager.AppSettings["DiseaseErrorThreshold"]))
                        .WithErrorCountThreshold(double.Parse(ConfigurationManager.AppSettings["DiseaseCountErrorThreshold"]))
                        .WithMaximumEpochCount(int.Parse(ConfigurationManager.AppSettings["DiseaseMaximumEpochCount"]))
                        .WithWeightRepositoryType(new DataBaseWeightRepository(NetworkTypeEnum.DiseaseNeuralNetwork), true)
                         .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
                         {
                             Mapper = new DiseasesTypeMapper(new DiseasesOutputMapper()),
                             NetworkType = NetworkTypeEnum.DiseaseNeuralNetwork
                         }.PopulateSamples())
                        .CreateNetwork(true)
                        .LearnNetwork();
                }
                else
                {
                    var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetworkSystemBLL.NeuralNetwork>();

                    var neuralNetwork = neuralNetworkBuilder
                        .WithLayerType(new NeuralLayer())
                        .WithActivationFunctionType(new SigmoidalActivationFunction())
                        .WithInductedFunctionType(new InductedLocalFieldFunction())
                        .WithLearningFunctionsType(new SigmoidalLearningFunctions())
                        .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                        .WithInputCount(int.Parse(ConfigurationManager.AppSettings["GroupRiskInputCount"]))
                        .WithHiddenLayersCount(int.Parse(ConfigurationManager.AppSettings["GroupRiskHiddenCount"]))
                        .WithHiddenLayersLength(int.Parse(ConfigurationManager.AppSettings["GroupRiskLenghtCount"]))
                        .WithOutputCount(int.Parse(ConfigurationManager.AppSettings["GroupRiskOutputCount"]))
                        .WithErrorThreshold(double.Parse(ConfigurationManager.AppSettings["GroupRiskErrorThreshold"]))
                        .WithErrorCountThreshold(double.Parse(ConfigurationManager.AppSettings["GroupRiskCountErrorThreshold"]))
                        .WithMaximumEpochCount(int.Parse(ConfigurationManager.AppSettings["GroupRiskMaximumEpochCount"]))
                        .WithWeightRepositoryType(
                        new DataBaseWeightRepository(networkType), true)
                        .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
                        {
                            Mapper = new DiseasesTypeMapper(new DiseaseGroupRiskOutputMapper()),
                            NetworkType = NetworkTypeEnum.InfarctGroupRiscNeuralNetwork
                        }
                        .PopulateSamples())
                        .CreateNetwork(true)
                        .LearnNetwork();
                }
            }
        }
    }
}
