using System.Configuration;
using System.Web.Http;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;

namespace NeuralNetworkSystemAPI.Controllers
{
    public class NeuralNetworkBaseController : ApiController
    {
        [NonAction]
        public INeuralNetwork GetDiseaseNeuralNetwork()
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
                .WithWeightRepositoryType(new DataBaseWeightRepository(NetworkTypeEnum.DiseaseNeuralNetwork), false)
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
                {
                    Mapper = new DiseasesTypeMapper(new DiseasesOutputMapper()),
                    NetworkType = NetworkTypeEnum.DiseaseNeuralNetwork
                }.PopulateSamples())
                .CreateNetwork(true);

            return neuralNetwork;
        }

        [NonAction]
        public INeuralNetwork GetGroupRiskNeuralNetwork(NetworkTypeEnum networkType)
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
                .WithWeightRepositoryType(new DataBaseWeightRepository(networkType), false)
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
                {
                    Mapper = new DiseasesTypeMapper(new DiseaseGroupRiskOutputMapper()),
                    NetworkType = networkType
                }
                .PopulateSamples())
                .CreateNetwork(true);

            return neuralNetwork;
        }
    }
}