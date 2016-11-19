using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.Interfaces
{
    public interface INeuralNetwork
    {
        IWeightRepository WeightRepository { get; set; }
        IEnumerable<ILayer> Layers { get; set; }

        ILayer GetOutputLayer();
        ILayer GetInputLayer();

        void Calculate(ILayer inputLauer);
    } 
}
