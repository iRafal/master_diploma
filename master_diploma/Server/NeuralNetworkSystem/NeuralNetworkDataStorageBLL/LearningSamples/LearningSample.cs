using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Learning;

namespace NeuralNetworkDataStorageBLL.LearningSamples
{
    public class LearningSample : ILearningSample
    {
        public ILayer InputLayer { get; set; }
        public ILayer OutputLayer { get; set; }
    }
}
