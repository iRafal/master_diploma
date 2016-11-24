using System.Web.Http;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers.OutPutMappers;
using NeuralNetworkSystemAPI.NeuralNetwork;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkSystemAPI.Controllers
{
    public class NeuralNetworkController : ApiController
    {
        public NeuralNetworkResponse GetForecast([FromUri]DiseaseMonitoringSample sample)
        {
            var mapper = new DiseasesTypeMapper(new DiseasesOutputMapper());

            var inputSample = mapper.MapToLearningSample<NeuralLayer, Neuron>(sample, new LearningSample());

            var input = NeuralNetworkSaver.NeuralNetwork.Normalize(inputSample.InputLayer);
            NeuralNetworkSaver.NeuralNetwork.Calculate(input);

            var output = NeuralNetworkSaver.NeuralNetwork.GetOutputLayer();

            var neuralResponse = new NeuralNetworkResponse
            {
                FirstOutput = output.Neurons[0].Value,
                SecondOuput = output.Neurons[1].Value
            };

            if (neuralResponse.FirstOutput > neuralResponse.SecondOuput)
            {
                neuralResponse.Diseases = 1;
                neuralResponse.Description = DiseasesStatusEnum.Normal.ToString();
            }
            else
            {
                neuralResponse.Diseases = 2;
                neuralResponse.Description = DiseasesStatusEnum.Infarct.ToString();
            }

            return neuralResponse;
        }
    }
}
