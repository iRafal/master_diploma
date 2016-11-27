using System;
using System.Linq;
using AutoMapper;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.LearningSamples;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.Interfaces.Components;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemAPI.NeuralNetwork
{
    public  class NeuralNetworkHelper
    {
        private readonly DiseasesTypeMapper _diseaseTypeMapper;

        public NeuralNetworkHelper( )
        {
            _diseaseTypeMapper = new DiseasesTypeMapper(new DiseasesOutputMapper());
        }

        public ILayer MapToInput(DieseaseRequestModel request, INeuralNetwork neuralNetwork,  bool normalize)
        {
            var sample = Mapper.Map<DieseaseRequestModel, DiseaseMonitoringSample>(request);

            var input = _diseaseTypeMapper.MapToLearningSample<NeuralLayer, Neuron>(sample, new LearningSample()).InputLayer;
          
            if (normalize)
            {
                input = neuralNetwork.Normalize(input);
            }

            return input;
        }

        public int GetNetwrokOutput(ILayer outPutLayer)
        {
            var value = outPutLayer.Neurons.Max(n => n.Value);
            var index = outPutLayer.Neurons.FindIndex(n => n.Value == value);

            return index + 1;
        } 
    }
}