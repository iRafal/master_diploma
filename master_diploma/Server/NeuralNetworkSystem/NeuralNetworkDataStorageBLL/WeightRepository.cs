using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL
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
            var  weight = _repository[updatedWeight.InputLayerIndex][updatedWeight.InputNeuronIndex].Single(w => w.OutputNeuronIndex == updatedWeight.OutputNeuronIndex);
            Console.WriteLine($"Weight on layer: {weight.InputLayerIndex} - {weight.InputNeuronIndex} - {weight.OutputNeuronIndex} with value: {weight.WeightValue} ->>>> {weight.NextIterationWeightValue}\n");
            return weight;
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
                var nextLayerNeuronsArray = layersArray[i + 1].Neurons.ToArray().Where(n => !n.IsThreshold);

                foreach (var currentNeuron in neuronsArray)
                {
                    var weightList = nextLayerNeuronsArray.Select(nextLayerNeuron => new Weight
                    {
                        InputLayerIndex = currentNeuron.LayerIndex, InputNeuronIndex = currentNeuron.ElementIndex, OutputLayerIndex = nextLayerNeuron.LayerIndex, OutputNeuronIndex = nextLayerNeuron.ElementIndex, WeightValue = _random.Next(-5, 5)/10
                    }).ToList();

                    _repository[i][currentNeuron.ElementIndex] = weightList;
                }
            }

            return this;
        }

        public void ChangeWeightsAfterIteration()
        {
            foreach (var layerValue in _repository.Values)
            {
                foreach (var neuronValues in layerValue.Values)
                {
                    foreach (var weight in neuronValues)
                    {
                        weight.WeightValue = weight.NextIterationWeightValue;
                    }
                }
            }
        }
    }
}
