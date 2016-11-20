using System;
using System.Collections.Generic;
using System.Linq;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Functions;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemBLL
{
    public class NeuralNetwork : INeuralNetwork
    {
        public IWeightRepository WeightRepository { get; set; }
        public ILearningSamplesRepository LeariningSamplesRepository { get; set; }
        public List<ILayer> Layers { get; set; }
        public ILearningFunctions LearningFunctions { get; set; }

        public ILayer GetOutputLayer()
        {
            return Layers.LastOrDefault();
        }

        public ILayer GetInputLayer()
        {
            return Layers.FirstOrDefault();
        }

        private Random _random;
        public void Calculate(ILayer inputLauer)
        {
            if (inputLauer.Neurons.Count() != GetInputLayer().Neurons.Count())
            {
                throw new Exception("Invalid number of input Elements");
            }         

            var layersCount = Layers.Count();
            Layers[0] = inputLauer;

            //Select second layer(maybe output)
            for (var i = 1; i < layersCount; i++)
            {
                var neurons = Layers[i].Neurons.Where(n => !n.IsThreshold);

                foreach (var t in neurons)
                {
                    var weights = GetWeighsForNeuronInput(t);

                    t.CalculateOutput(Layers[i - 1].Neurons, weights);                 
                }
            }
        }

        public void LearnNetwork()
        {
           
            if (WeightRepository == null)
            {
                throw new NullReferenceException("Weight repository are empty");
            }

            if (LeariningSamplesRepository == null)
            {
                throw new NullReferenceException("Learning Samples repository are empty");
            }

            for (int i = 0; i < 60; i++)
            {
                foreach (var sample in LeariningSamplesRepository.LearningSamples)
                {
                    //back propogation
                    FrontIteration(sample);
                    BackIteration();
                    WeightRepository.ChangeWeightsAfterIteration();
                }

                LeariningSamplesRepository.LearningSamples = ReorderSamples(LeariningSamplesRepository.LearningSamples);
            }
            

        }

        private List<ILearningSample> ReorderSamples(List<ILearningSample> samples )
        {
            _random = new Random();
            var samplesLenght = samples.Count();

            for (int i = 0; i < samplesLenght; i++)
            {
                var source = _random.Next(0, samplesLenght - 1);
                var target = _random.Next(0, samplesLenght - 1);

                var sourceSample = samples[source];
                var targetSample = samples[target];

                samples[target] = sourceSample;
                samples[source] = targetSample;
            }

            return samples;
        }

        private List<Weight> GetWeighsForNeuronInput(INeuron neuron)
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

        private List<Weight> GetWeighsForNeuronOutput(INeuron neuron)
        {
            var currentLayerIndex = neuron.LayerIndex;
            var currentLayer = Layers.ToArray()[currentLayerIndex];

            if (currentLayer.IsOutputLayer)
            {
                return  new List<Weight>();
            }

            var nextLayerIndex = neuron.LayerIndex + 1;
            var currentNeuronIndex = neuron.ElementIndex;

            var nextLayer = Layers.ToArray()[nextLayerIndex];

            return nextLayer.Neurons.Where(n => !n.IsThreshold).Select(nextNeuron => WeightRepository.GetWeight(currentLayerIndex, nextLayerIndex, currentNeuronIndex, nextNeuron.ElementIndex)).ToList();
        }

        private void FrontIteration(ILearningSample sample)
        {
            this.Calculate(sample.InputLayer);

            var outputLayer = GetOutputLayer();

            foreach (var neuron in outputLayer.Neurons)
            {
                neuron.Error = sample.OutputLayer.Neurons.Single(n => n.ElementIndex == neuron.ElementIndex).Value - neuron.Value;
            }
        }

        private void BackIteration()
        {           
            var layersCount = Layers.Count;

            for (var i = layersCount - 1; i > 0; i--)
            {
                var neuronsArray = Layers[i].Neurons.Where(n => !n.IsThreshold).ToList();
                var neuronsCount = neuronsArray.Count();

                for (var j = 0; j < neuronsCount; j++)
                {
                    if (Layers[i].IsOutputLayer)
                    {
                        LearningFunctions.GetOutPutGradient(neuronsArray[j]);
                    }
                    else
                    {
                        LearningFunctions.GetHiddenGradient(neuronsArray[j], GetWeighsForNeuronOutput(neuronsArray[j]), Layers[i + 1].Neurons.Where(n => !n.IsThreshold).ToList());
                    }

                    var previousLayerNeurons = Layers[i - 1].Neurons.ToArray();
                    var previousWeights = GetWeighsForNeuronInput(neuronsArray[j]).ToArray();

                    for (var k = 0; k < previousWeights.Length; k++)
                    {
                        var neuronValue = previousLayerNeurons[k].Value;
                        var delta = LearningFunctions.LearningSpeed * neuronsArray[j].Gradient * neuronValue;
                        previousWeights[k].NextIterationWeightValue = previousWeights[k].WeightValue + delta;
                        WeightRepository.UpdateWeight(previousWeights[k]);

                    }

                }
            }
        }
    }
}
