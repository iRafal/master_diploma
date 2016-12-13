using System;
using System.Configuration;
using System.Linq;
using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;
using AutoMapper;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;

namespace NeuralNetworkSystemAPI
{
    public class WebApiApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            MapperConfigurate();
            //CreateDiseaseNetowrk();
            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
        }

        public void CreateDiseaseNetowrk()
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

        public void MapperConfigurate()
        {
            Mapper.Initialize(cfg =>
            {
                cfg.CreateMap<DieseaseRequestModel, DiseaseMonitoringSample>();
                cfg.CreateMap<DiseaseLearningSampleModel, DiseaseMonitoringSample>();
            }                 
            );
        }
    }
}
