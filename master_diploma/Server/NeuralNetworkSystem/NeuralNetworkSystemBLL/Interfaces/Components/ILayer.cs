using System.Collections.Generic;

namespace NeuralNetworkSystemBLL.Interfaces.Components
{
    public interface ILayer
    {
        IEnumerable<INeuron> Neurons { get; set; }
        int LayerIndex { get; set; }
        bool IsInputLayer { get; set; }
        bool IsOutputLayer { get; set; }
    }
}
