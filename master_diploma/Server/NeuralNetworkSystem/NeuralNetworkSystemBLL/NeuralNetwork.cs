using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL
{
    public class NeuralNetwork : INeuralNetwork
    {
        public IWeightRepository WeightRepository { get; set; }
        public IEnumerable<ILayer> Layers { get; set; }

        public ILayer GetOutputLayer()
        {
            return Layers.LastOrDefault();
        }

        public ILayer GetInputLayer()
        {
            return Layers.FirstOrDefault();
        }

        public void Calculate(ILayer inputLauer)
        {
            if (inputLauer.Neurons.Count() != GetInputLayer().Neurons.Count())
            {
                throw new Exception("Invalid number of input Elements");
            }         

            var layersCount = Layers.Count();
            var layersArray = Layers.ToArray();
            layersArray[0] = inputLauer;

            //Select second layer(maybe output)
            for (var i = 1; i < layersCount; i++)
            {
                var neurons = layersArray[i].Neurons;

                foreach (var t in neurons)
                {
                    var weights = GetWeighsForNeuron(t);

                    t.CalculateOutput(layersArray[i - 1].Neurons, weights);
                }
            }
        }

        private IEnumerable<Weight> GetWeighsForNeuron(INeuron neuron)
        {
            var previousLayerIndex = neuron.LayerIndex - 1;
            var currentLayerIndex = neuron.LayerIndex;
            var currentNeuronIndex = neuron.ElementIndex;

            if (previousLayerIndex < 0)
            {
                throw new Exception("Neuron should be not in input Layer");
            }

            var previousLayer = Layers.ToArray()[previousLayerIndex];

            var weightList = previousLayer.Neurons
                .Select(neuronInLayer => 
                WeightRepository
                .GetWeight(previousLayerIndex, currentLayerIndex, neuronInLayer.ElementIndex, currentNeuronIndex))
                .ToList();

            return weightList;
        }
    }
}
