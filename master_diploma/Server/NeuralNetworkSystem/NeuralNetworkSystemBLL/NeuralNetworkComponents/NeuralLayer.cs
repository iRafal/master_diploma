using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents
{
    public class NeuralLayer : ILayer
    {
        public List<INeuron> Neurons { get; set; }
        public int LayerIndex { get; set; }
        public bool IsInputLayer { get; set; }
        public bool IsOutputLayer { get; set; }

        public override string ToString()
        {
            var type = string.Empty;

            if (IsInputLayer)
            {
                type = "(Input Layer)";
            }
            if (IsOutputLayer)
            {
                type = "(Output Layer)";
            }
            if (!IsInputLayer & !IsOutputLayer)
            {
                type = "(Hidden Field)";
            }

            return "Index - " + LayerIndex + " " + type;

        }
    }
}
