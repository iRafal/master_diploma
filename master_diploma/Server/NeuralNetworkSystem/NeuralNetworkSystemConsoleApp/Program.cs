using NeuralNetworkDataStorageBLL.DTORepositories;
using NeuralNetworkDataStorageBLL.Enums;

namespace NeuralNetworkSystemConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            //var neuralNetworkBuilder = new NeuralNetworkBuilder<NeuralNetworkSystemBLL.NeuralNetwork>();

            //var repo = new DataBaseDiseasesLearningSamplesRepository();

            //repo.Generate();




            //var neuralNetwork = neuralNetworkBuilder
            //    .WithLayerType(new NeuralLayer())
            //    .WithActivationFunctionType(new SigmoidalActivationFunction())
            //    .WithInductedFunctionType(new InductedLocalFieldFunction())
            //    .WithLearningFunctionsType(new SigmoidalLearningFunctions())
            //    .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
            //    .WithInputCount(21)
            //    .WithHiddenLayersCount(1)
            //    .WithHiddenLayersLength(15)
            //    .WithOutputCount(2)
            //    .WithErrorThreshold(0.4)
            //    .WithErrorCountThreshold(5)
            //    .WithMaximumEpochCount(0)
            //    .WithWeightRepositoryType(new DataBaseWeightRepository(NetworkTypeEnum.InfarctGroupRiscNeuralNetwork), true)
            //    .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
            //    {
            //        Mapper = new DiseasesTypeMapper(new DiseaseGroupRiskOutputMapper()),
            //        NetworkType = NetworkTypeEnum.InfarctGroupRiscNeuralNetwork
            //    }
            //    .PopulateSamples())
            //    .CreateNetwork(true)
            //    .LearnNetwork();

            //var neuralNetwork = neuralNetworkBuilder
            //           .WithLayerType(new NeuralLayer())
            //           .WithActivationFunctionType(new SigmoidalActivationFunction())
            //           .WithInductedFunctionType(new InductedLocalFieldFunction())
            //           .WithLearningFunctionsType(new SigmoidalLearningFunctions())
            //           .WithNeuronBuilerType(new NeuronBuilder(), new Neuron())
            //           .WithInputCount(21)
            //           .WithHiddenLayersCount(3)
            //           .WithHiddenLayersLength(42)
            //           .WithOutputCount(2)
            //           .WithErrorThreshold(0.35)
            //           .WithErrorCountThreshold(2)
            //           .WithMaximumEpochCount(0)
            //           .WithWeightRepositoryType(new DataBaseWeightRepository(NetworkTypeEnum.DiseaseNeuralNetwork), true)
            //            .WithLearningSamplesRepositoryType(new DataBaseDiseasesLearningSamplesRepository()
            //            {
            //                Mapper = new DiseasesTypeMapper(new DiseasesOutputMapper()),
            //                NetworkType = NetworkTypeEnum.DiseaseNeuralNetwork
            //            }.PopulateSamples())
            //           .CreateNetwork(true)
            //           .LearnNetwork();
        }
    }
}
