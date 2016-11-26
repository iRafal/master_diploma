using System;
using System.Collections.Generic;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkSystemBLL.Attributes;
using NeuralNetworkSystemBLL.Interfaces.AttributeMappers;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL.LearningSamples.Mappers
{
    public class DiseasesTypeMapper
    {
        private readonly IOutputAttributeMapper _outputAttributeMapper;

        public DiseasesTypeMapper(IOutputAttributeMapper outputAttributeMapper)
        {
            _outputAttributeMapper = outputAttributeMapper;
        }

        public ILearningSample MapToLearningSample<TLAyer,TNeuron>(DiseaseMonitoringSample disease, ILearningSample sample) where TLAyer : ILayer, new() where TNeuron: INeuron, new()
        {
            var typeProperties = disease.GetType().GetProperties();
            var inputLayer = new TLAyer
            {
                IsInputLayer = true,
                LayerIndex = 0,
                Neurons = new List<INeuron>
                {
                    new Neuron
                    {
                        ElementIndex = 0,
                        LayerIndex = 0,
                        Value = 1
                    }
                }
            };

            foreach (var prop in typeProperties)
            {
                var attributes = prop.GetCustomAttributes(true);

                foreach (var attribute in attributes)
                {
                    var inputAttibute = attribute as InputTrainingSampleAttribute;

                    if (inputAttibute != null)
                    {
                        var newNeuron = new TNeuron
                        {
                            LayerIndex = 0,
                            ElementIndex = inputAttibute.InputNumber,
                            Value = Convert.ToDouble(prop.GetValue(disease, null))
                        };

                        inputLayer.Neurons.Add(newNeuron);
                    }

                    var outPutAttribute = attribute as OutputTrainingSampleAttribute;

                    if (outPutAttribute != null)
                    {
                        var outPutLayer = _outputAttributeMapper.Map(prop, disease, new TLAyer());
                        if (outPutLayer != null)
                        {
                            sample.OutputLayer = outPutLayer;
                        }                       
                    }                
                }
            }

            sample.InputLayer = inputLayer;

            return sample;
        }
    }
}
