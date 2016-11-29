﻿using System;
using System.Web;
using System.Web.Http;
using AutoMapper;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.DTORepositories;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Repositories;
using NeuralNetworkSystemAPI.Models;
using NeuralNetworkSystemAPI.NeuralNetwork;

namespace NeuralNetworkSystemAPI.Controllers
{
    public class NeuralNetworkController : NeuralNetworkBaseController
    {
        private readonly NeuralNetworkHelper _neuralNetworkHelper;
        private readonly DataBaseSuggestionRepository _suggestionRepository;

        public NeuralNetworkController()
        {
            _neuralNetworkHelper = new NeuralNetworkHelper();
            _suggestionRepository = new DataBaseSuggestionRepository();
        }

        public NeuralNetworkResponse GetForecast([FromUri]DieseaseRequestModel requestModel)
        {
            var neuralNetwork = GetDiseaseNeuralNetwork();
            var input = _neuralNetworkHelper.MapToInput(requestModel, neuralNetwork, true);

            neuralNetwork.Calculate(input);
            var output = _neuralNetworkHelper.GetNetwrokOutput(neuralNetwork.GetOutputLayer());
            var disease = (DiseasesStatusEnum)Enum.ToObject(typeof(DiseasesStatusEnum), output);

            var suggestionList = _suggestionRepository.GetAllSuggestions(disease);


            var groupRisk = RiskStatusEnum.Hight;

            if (disease != DiseasesStatusEnum.Normal)
            {
                var networkType = (NetworkTypeEnum)Enum.ToObject(typeof(NetworkTypeEnum), output);
                neuralNetwork = GetGroupRiskNeuralNetwork(networkType);

                neuralNetwork.Calculate(input);
                output = _neuralNetworkHelper.GetNetwrokOutput(neuralNetwork.GetOutputLayer());
                groupRisk = (RiskStatusEnum)Enum.ToObject(typeof(RiskStatusEnum), output);
            }

            var diseaseResponse = new DiseaseResponseObjectModel()
            {
                Disease = new Disease
                {
                    Status = disease,
                    Name = disease.ToString()
                },
                GroupRisk = new GroupRisk
                {
                    GroupRiskType = groupRisk,
                    Name = groupRisk.ToString()
                },
                Suggestions = suggestionList
            };

            var neuralResponse = new NeuralNetworkResponse();
            neuralResponse.Forecasts.Add(diseaseResponse);

            return neuralResponse;
        }

        public HttpResponse PostSample(DiseaseLearningSampleModel learningSample)
        {
            var dbLearningSample = Mapper.Map<DiseaseLearningSampleModel, DiseaseMonitoringSample>(learningSample);

            dbLearningSample.Disease = new Disease
            {
                Status = learningSample.DiseaseStatus,
                Name = learningSample.DiseaseStatus.ToString()
            };

            dbLearningSample.GroupRisk = new GroupRisk
            {
                GroupRiskType = learningSample.GroupRiskStatus,
                Name = learningSample.GroupRiskStatus.ToString()
            };

            var repo = new DataBaseDiseasesLearningSamplesRepository
            {
                NetworkType = NetworkTypeEnum.DiseaseNeuralNetwork
            };

            repo.SaveNewSample(dbLearningSample);

            var diseaseNeuralNetwork = GetDiseaseNeuralNetwork();
            diseaseNeuralNetwork.LearnNetwork();

            var groupRiskNeuralNetwork = GetGroupRiskNeuralNetwork((NetworkTypeEnum)Enum.ToObject(typeof(NetworkTypeEnum), (int)learningSample.DiseaseStatus));
            groupRiskNeuralNetwork.LearnNetwork();

            return null;
        }
    }
}
