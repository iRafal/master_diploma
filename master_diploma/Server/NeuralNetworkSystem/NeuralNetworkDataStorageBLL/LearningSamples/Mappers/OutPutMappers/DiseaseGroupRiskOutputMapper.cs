using System;
using System.Linq;
using System.Reflection;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkSystemBLL.Interfaces.AttributeMappers;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers
{
    public class DiseaseGroupRiskOutputMapper : IOutputAttributeMapper
    {
        private INeuron[] _neurons;

        public ILayer Map(PropertyInfo property, object obj, ILayer layer)
        {
            _neurons = new INeuron[Enum.GetNames(typeof(RiskStatusEnum)).Length];

            for (var i = 0; i < _neurons.Length; i++)
            {
                _neurons[i] = new Neuron()
                {
                    ElementIndex = i
                };
            }

            layer.Neurons = _neurons.ToList();
            layer.IsOutputLayer = true;

            var diseases = property.GetValue(obj, null) as GroupRisk;

            if (diseases != null)
            {
                layer.Neurons[(int)diseases.GroupRiskType - 1].Value = 1;
            }
            else
            {
                return null;
            }

            return layer;
        }
    }
}
