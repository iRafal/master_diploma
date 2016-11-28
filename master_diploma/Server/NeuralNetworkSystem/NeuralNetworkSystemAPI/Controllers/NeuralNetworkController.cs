﻿using System;
using System.Web.Http;
using NeuralNetworkDataStorageBLL.DTORepositories;
using NeuralNetworkDataStorageBLL.Enums;
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

            var neuralResponse = new NeuralNetworkResponse
            {
                Disease = (int) disease,
                DiseaseDescription = disease.ToString(),
                GroupRisk = (int) groupRisk,
                GroupRiskDescription = groupRisk.ToString(),
                Suggestions = suggestionList
            };

            return neuralResponse;
        }
    }
}
