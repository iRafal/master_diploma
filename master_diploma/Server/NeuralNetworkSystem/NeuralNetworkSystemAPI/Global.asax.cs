using System.Web.Http;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
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
            CreateNetowrk();
            AreaRegistration.RegisterAllAreas();
            GlobalConfiguration.Configure(WebApiConfig.Register);
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
        }

        public void CreateNetowrk()
        {
            var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetworkSystemBLL.NeuralNetwork>();

            var neuralNetwork = neuralNetworkBuilder
                .WithLayerType(new NeuralLayer())
                .WithActivationFunctionType(new SigmoidalActivationFunction())
                .WithInductedFunctionType(new InductedLocalFieldFunction())
                .WithLearningFunctionsType(new SigmoidalLearningFunctions())
                .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
                .WithInputCount(21)
                .WithHiddenLayersCount(2)
                .WithHiddenLayersLength(30)
                .WithOutputCount(2)
                .WithMaximumEpochCount(1000)
                .WithWeightRepositoryType(new WeightRepository())
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository())
                .CreateNetwork(true)
                .LearnNetwork();

            NeuralNetworkSaver.NeuralNetwork = neuralNetwork;
      }
    }
}
