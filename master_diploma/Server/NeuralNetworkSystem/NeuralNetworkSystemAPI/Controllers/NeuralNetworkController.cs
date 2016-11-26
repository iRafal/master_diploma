using System;
using System.Web.Http;
using NeuralNetworkDataStorageBLL;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemAPI.NeuralNetwork;
using NeuralNetworkSystemBLL.Builders;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.NeuralNetworkComponents.Functions;

namespace NeuralNetworkSystemAPI.Controllers
{
    public class NeuralNetworkController : ApiController
    {
        public NeuralNetworkResponse GetForecast([FromUri]DieseaseRequestModel requestModel)
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
                .WithWeightRepositoryType(new DataBaseWeightRepository(WeightTypeEnum.DiseaseNeuralNetwork), false)
                .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository())
                .CreateNetwork(true);

            var neuralNetworkHelper = new NeuralNetworkHelper();

            var input = neuralNetworkHelper.MapToInput(requestModel, neuralNetwork, true);

            neuralNetwork.Calculate(input);

            var output = neuralNetworkHelper.GetNetwrokOutput(neuralNetwork.GetOutputLayer());

            var disease = (DiseasesStatusEnum)Enum.ToObject(typeof(DiseasesStatusEnum), output);

            var neuralResponse = new NeuralNetworkResponse
            {
                Disease = (int) disease,
                Description = disease.ToString()
            };

            return neuralResponse;
        }
    }
}
