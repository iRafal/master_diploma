using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL
{
    public class WeightRepository : IWeightRepository
    {
        //Layer -> Neuron -> All weight from that neuron.
        private readonly Dictionary<int, Dictionary<int, IEnumerable<Weight>>> _repository;
        private readonly Random _random;

        public WeightRepository()
        {
            _repository = new Dictionary<int, Dictionary<int, IEnumerable<Weight>>>();
            _random = new Random();
        }

        public Weight GetWeight(int inputLayerIndex, int outputLayerIndex, int inputNeuronIndex, int outPutNeuronIndex)
        {
            var weightslist = _repository[inputLayerIndex][inputNeuronIndex];

            return weightslist.FirstOrDefault(w => w.OutputNeuronIndex == outPutNeuronIndex & w.OutputLayerIndex == outputLayerIndex);
        }

        public Weight UpdateWeight(Weight updatedWeight)
        {
            throw new NotImplementedException();
        }

        public IWeightRepository PopulateRepository()
        {
            throw new NotImplementedException();
        }

        public IWeightRepository CreateStartUpRepository(INeuralNetwork neuralNetwork)
        {
            var layersArray = neuralNetwork.Layers.ToArray();
            var layersCount = layersArray.Length;
            

            for (var i = 0; i < layersCount - 1; i++)
            {
                //Creating a layer
                _repository[i] = new Dictionary<int, IEnumerable<Weight>>();

                var neuronsArray = layersArray[i].Neurons.ToArray();
                var nextLayerNeuronsArray = layersArray[i + 1].Neurons.ToArray();              

                foreach (var currentNeuron in neuronsArray)
                {
                    var weightList = new List<Weight>();

                    foreach (var nextLayerNeuron in nextLayerNeuronsArray)
                    {
                        var newWeight = new Weight
                        {
                            InputLayerIndex = currentNeuron.LayerIndex,
                            InputNeuronIndex = currentNeuron.ElementIndex,
                            OutputLayerIndex = nextLayerNeuron.LayerIndex,
                            OutputNeuronIndex = nextLayerNeuron.ElementIndex,
                            WeightValue = _random.Next(1, 100)
                        }; 

                        weightList.Add(newWeight);

                    }

                    _repository[i][currentNeuron.ElementIndex] = weightList;
                }
            }

            return this;
        }
    }
}
