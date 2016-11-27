using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;
using AutoMapper;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemAPI.NeuralNetwork;
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
            CreateDiseaseNetowrk();
            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
        }

        public void CreateDiseaseNetowrk()
        {
            var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetworkSystemBLL.NeuralNetwork>();

            var neuralNetwork = neuralNetworkBuilder
                .WithLayerType(new NeuralLayer())
                .WithActivationFunctionType(new SigmoidalActivationFunction())
                .WithInductedFunctionType(new InductedLocalFieldFunction())
                .WithLearningFunctionsType(new SigmoidalLearningFunctions())
                .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                .WithInputCount(21)
                .WithHiddenLayersCount(3)
                .WithHiddenLayersLength(42)
                .WithOutputCount(2)
                .WithErrorThreshold(0.35)
                .WithErrorCountThreshold(2)
                .WithMaximumEpochCount(0)
                .WithWeightRepositoryType(new DataBaseWeightRepository(WeightTypeEnum.DiseaseNeuralNetwork), true)
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository())
                .CreateNetwork(true)
                .LearnNetwork();

            //NeuralNetworkSaver.DiseaseNeuralNetwork = neuralNetwork;
        }

        public void MapperConfigurate()
        {
            Mapper.Initialize(cfg => cfg.CreateMap<DieseaseRequestModel,DiseaseMonitoringSample>());
        }
    }
}
