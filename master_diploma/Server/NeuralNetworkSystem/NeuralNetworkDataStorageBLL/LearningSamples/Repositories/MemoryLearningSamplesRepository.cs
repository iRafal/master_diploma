using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL.LearningSamples.Repositories
{
    public class MemoryLearningSamplesRepository : ILearningSamplesRepository
    {
        public List<ILearningSample> LearningSamples { get; set; }
        public ILearningSamplesRepository PopulateSamples()
        {
            throw new NotImplementedException();
        }

        private Random _random;

        public MemoryLearningSamplesRepository()
        {
            var learningSamples = new List<ILearningSample>();
            _random = new Random();


            for (int i = 50; i < 100; i++)
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
                    Value = i
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
                    Value = 1
                };

                var newLearningSample = new LearningSample
                {
                    InputLayer = new NeuralLayer
                    {
                        IsInputLayer = true,
                        Neurons = new List<INeuron>
                            {
                                zeroNeuron,firstInputNeuron,//secondInputNeuron
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




            for (int i = 100; i < 150; i++)
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
                    Value = i
                };


                var firstOutputNeuron = new Neuron
                {
                    ElementIndex = 0,
                    LayerIndex = 0,
                    Value = 1
                };

                var secondOutputNeuron = new Neuron
                {
                    ElementIndex = 1,
                    LayerIndex = 0,
                    Value = 0
                };

                var newLearningSample = new LearningSample
                {
                    InputLayer = new NeuralLayer
                    {
                        IsInputLayer = true,
                        Neurons = new List<INeuron>
                            {
                                zeroNeuron,firstInputNeuron,//secondInputNeuron
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






            //for (var i = 0; i < 200; i++)
            //{
            //    var zeroNeuron = new Neuron
            //    {
            //        ElementIndex = 0,
            //        LayerIndex = 0,
            //        Value = 1
            //    };

            //    var firstInputNeuron = new Neuron
            //    {
            //        ElementIndex = 1,
            //        LayerIndex = 0,
            //        Value = _random.Next(0, 200)
            //    };

            //    //var secondInputNeuron = new Neuron
            //    //{
            //    //    ElementIndex = 2,
            //    //    LayerIndex = 0,
            //    //    Value = _random.Next(1, 100)
            //    //};


            //    var firstOutputNeuron = new Neuron
            //    {
            //        ElementIndex = 0,
            //        LayerIndex = 0,
            //        Value = 0
            //    };

            //    var secondOutputNeuron = new Neuron
            //    {
            //        ElementIndex = 1,
            //        LayerIndex = 0,
            //        Value = 0
            //    };

            //    //var sum = firstInputNeuron.Value + secondInputNeuron.Value;

            //    if (firstInputNeuron.Value >= 40)
            //    {
            //        firstOutputNeuron.Value = 1;
            //    }
            //    else
            //    {
            //        secondOutputNeuron.Value = 1;
            //    }

            //    var newLearningSample = new LearningSample
            //    {
            //        InputLayer = new NeuralLayer
            //        {
            //            IsInputLayer = true,
            //            Neurons = new List<INeuron>
            //            {
            //                zeroNeuron,firstInputNeuron,//secondInputNeuron
            //            }
            //        },
            //        OutputLayer = new NeuralLayer
            //        {
            //            IsOutputLayer = true,
            //            Neurons = new List<INeuron>()
            //            {
            //                firstOutputNeuron,
            //                secondOutputNeuron
            //            }
            //        }

            //    };

            //    learningSamples.Add(newLearningSample);

            //}

            LearningSamples = learningSamples;
        }

    }
}
