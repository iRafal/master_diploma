using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.Interfaces.Learning
{
    public interface ILearningSample
    {
        ILayer InputLayer { get; set; }
        ILayer OutputLayer { get; set; }
    }
}
