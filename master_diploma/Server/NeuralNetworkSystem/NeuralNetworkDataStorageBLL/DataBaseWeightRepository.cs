using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkSystemBLL.Interfaces;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;

namespace NeuralNetworkDataStorageBLL
{
    public class DataBaseWeightRepository : IWeightRepository
    {
        //Layer -> Neuron -> All weight from that neuron.
        private readonly Dictionary<int, Dictionary<int, IEnumerable<Weight>>> _repository;
        private readonly Random _random;
        private readonly string _connectionString;

        private readonly NetworkTypeEnum _weightType;

        public DataBaseWeightRepository(NetworkTypeEnum weightType)
        {
            _weightType = weightType;
            _connectionString = ConfigurationManager.ConnectionStrings["NeuralNetworkDB"].ConnectionString;
            _repository = new Dictionary<int, Dictionary<int, IEnumerable<Weight>>>();
            _random = new Random();
        }

        public Weight GetWeight(int inputLayerIndex, int outputLayerIndex, int inputNeuronIndex, int outPutNeuronIndex)
        {
            var weightslist = _repository[inputLayerIndex][inputNeuronIndex];

            return weightslist.FirstOrDefault(w => w.OutputNeuronIndex == outPutNeuronIndex & w.OutputLayerIndex == outputLayerIndex);
        }

        public void UpdateWeights()
        {
            CleanWeightRepository();

            foreach (var layerValue in _repository.Values)
            {
                foreach (var neuronValues in layerValue.Values)
                {
                    foreach (var weight in neuronValues)
                    {
                        SaveWeightsInDataBase(weight);
                    }
                }
            }
        }

        public IWeightRepository PopulateRepository()
        {
            var weightList = GetAll();

            var layerGroups = weightList.GroupBy(l => l.InputLayerIndex).ToList();

            foreach (var layer in layerGroups)
            {
                _repository[layer.First().InputLayerIndex] = new Dictionary<int, IEnumerable<Weight>>();

                var neuronsInLayer = layer.GroupBy(l => l.InputNeuronIndex);

                foreach (var neuron in neuronsInLayer)
                {                   
                    _repository[layer.First().InputLayerIndex][neuron.First().InputNeuronIndex] = neuron.Select(n => n);
                }
            }

            return this;
        }

        public IWeightRepository CreateStartUpRepository(INeuralNetwork neuralNetwork)
        {
            CleanWeightRepository();

            var layersArray = neuralNetwork.Layers;
            var layersCount = layersArray.Count();


            for (var i = 0; i < layersCount - 1; i++)
            {
                //Creating a layer
                _repository[i] = new Dictionary<int, IEnumerable<Weight>>();

                var neuronsArray = layersArray[i].Neurons;
                var nextLayerNeuronsArray = layersArray[i + 1].Neurons.Where(n => !n.IsThreshold);

                foreach (var currentNeuron in neuronsArray)
                {
                    var weightList = nextLayerNeuronsArray.Select(nextLayerNeuron => new Weight
                    {
                        InputLayerIndex = currentNeuron.LayerIndex,
                        InputNeuronIndex = currentNeuron.ElementIndex,
                        OutputLayerIndex = nextLayerNeuron.LayerIndex,
                        OutputNeuronIndex = nextLayerNeuron.ElementIndex,
                        WeightValue = Convert.ToDouble(_random.Next(-5, 5)) / 10
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

        #region DbActions
        private void CleanWeightRepository()
        {
            var queryString = "DELETE FROM Weights WHERE Weights.WeightTypeID = " + (int) _weightType;

            using (var connection = new SqlConnection(_connectionString))
            {
                using (var command = new SqlCommand(queryString, connection))
                {
                    connection.Open();
                    command.ExecuteNonQuery();
                }              
            }
        }

        private void SaveWeightsInDataBase(Weight weight)
        {
            var queryString =
                "INSERT INTO Weights(InputLayerIndex, OutputLayerIndex, InputNeuronIndex, OutputNeuronIndex, WeightValue, WeightTypeID) " +
                " VALUES (@InputLayerIndex, @OutputLayerIndex, @InputNeuronIndex, @OutputNeuronIndex, @WeightValue, @WeightTypeID)";

            using (var connection = new SqlConnection(_connectionString))
            {
                using (var command = new SqlCommand(queryString, connection))
                {
                    command.Parameters.Add("@InputLayerIndex", SqlDbType.Int).Value = weight.InputLayerIndex;
                    command.Parameters.Add("@OutputLayerIndex", SqlDbType.Int).Value = weight.OutputLayerIndex;
                    command.Parameters.Add("@InputNeuronIndex", SqlDbType.Int).Value = weight.InputNeuronIndex;
                    command.Parameters.Add("@OutputNeuronIndex", SqlDbType.Int).Value = weight.OutputNeuronIndex;
                    command.Parameters.Add("@WeightValue", SqlDbType.Float).Value = weight.WeightValue;
                    command.Parameters.Add("@WeightTypeID", SqlDbType.Float).Value = (int)_weightType;

                    connection.Open();
                    int result = command.ExecuteNonQuery();
                }
            }
        }

        private IEnumerable<Weight> GetAll()
        {
            var resultList = new List<Weight>();
            var queryString = "SELECT Weights.* " +
                              " FROM Weights WHERE WeightTypeID = " + (int)_weightType;

            using (var connection = new SqlConnection(_connectionString))
            {
                var command = new SqlCommand(queryString, connection);
                connection.Open();
                var dataReader = command.ExecuteReader();

                while (dataReader.Read())
                {
                    resultList.Add(GetWeightFromReader(dataReader));
                }
            }

            return resultList;
        }

        private Weight GetWeightFromReader(IDataRecord reader)
        {
            var newWeight = new Weight
            {
                InputLayerIndex = reader["InputLayerIndex"] != null ? Convert.ToInt32(reader["InputLayerIndex"]) : 0,
                OutputLayerIndex = reader["OutputLayerIndex"] != null ? Convert.ToInt32(reader["OutputLayerIndex"]) : 0,
                InputNeuronIndex = reader["InputNeuronIndex"] != null ? Convert.ToInt32(reader["InputNeuronIndex"]) : 0,
                OutputNeuronIndex = reader["OutputNeuronIndex"] != null ? Convert.ToInt32(reader["OutputNeuronIndex"]) : 0,
                WeightValue = reader["WeightValue"] != null ? Convert.ToDouble(reader["WeightValue"]) : 0
            };

            return newWeight;
        }
        #endregion
    }
}
