using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL.LearningSamples
{
    public class MemoryLearningSamplesRepository : ILearningSamplesRepository
    {
        public List<ILearningSample> LearningSamples { get; set; }

        private Random _random;

        public MemoryLearningSamplesRepository()
        {
            var learningSamples = new List<ILearningSample>();
            _random = new Random();

            for (var i = 0; i < 1000; i++)
            {
                var zeroNeuron = new Neuron
                {
                    ElementIndex = 0,
                    LayerIndex = 0,
                    Value = 1
                };

                var firstInputNeuron = new Neuron
                {
                    ElementIndex = 1,
                    LayerIndex = 0,
                    Value = _random.Next(1, 30)
                };


                var firstOutputNeuron = new Neuron
                {
                    ElementIndex = 0,
                    LayerIndex = 0,
                    Value = 0
                };

                var secondOutputNeuron = new Neuron
                {
                    ElementIndex = 1,
                    LayerIndex = 0,
                    Value = 0
                };

                if (firstInputNeuron.Value >= 10)
                {
                    firstOutputNeuron.Value = 1;
                }
                else
                {
                    secondOutputNeuron.Value = 1;
                }

                var newLearningSample = new LearningSample
                {
                    InputLayer = new NeuralLayer
                    {
                        IsInputLayer = true,
                        Neurons = new List<INeuron>
                        {
                            zeroNeuron,firstInputNeuron
                        }
                    },
                    OutputLayer = new NeuralLayer
                    {
                        IsOutputLayer = true,
                        Neurons = new List<INeuron>()
                        {
                            firstOutputNeuron,
                            secondOutputNeuron
                        }
                    }
                    
                };

                learningSamples.Add(newLearningSample);

            }

            LearningSamples = learningSamples;
        }
    }
}
