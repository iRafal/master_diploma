using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.NeuralNetworkComponents
{
    public class Weight
    {
        public int InputLayerIndex { get; set; }
        public int OutputLayerIndex { get; set; }
        public int InputNeuronIndex { get; set; }
        public int OutputNeuronIndex { get; set; }      
        public double WeightValue { get; set; }

        public double NextIterationWeightValue { get; set; }


        public override string ToString()
        {
            return $"({InputLayerIndex} - {InputNeuronIndex}) ---> ({OutputLayerIndex} - {OutputNeuronIndex})";
        }
    }
}
