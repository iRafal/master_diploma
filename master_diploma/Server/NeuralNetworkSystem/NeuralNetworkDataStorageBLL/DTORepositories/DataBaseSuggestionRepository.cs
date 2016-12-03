using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;

namespace NeuralNetworkDataStorageBLL.DTORepositories
{
    public class DataBaseSuggestionRepository
    {
        private string _connectionString;

        public DataBaseSuggestionRepository()
        {
            _connectionString = ConfigurationManager.ConnectionStrings["NeuralNetworkDB"].ConnectionString;
        }

        public List<Suggestion> GetAllSuggestions(DiseasesStatusEnum diseases)
        {
            var resultList = new List<Suggestion>();
            var queryString =
                "SELECT Suggestions.*, Parameters.ParameterID, Parameters.Description AS ParameterDescription, DiseaseStatus.DiseaseStatusID AS DiseaseID, DiseaseStatus.DiseaseName AS DiseaseDescription " +
                " FROM Suggestions LEFT JOIN Parameters ON Suggestions.ParameterID = Parameters.ParameterID " +
                " LEFT JOIN DiseaseStatus ON Suggestions.DiseaseStatusID = DiseaseStatus.DiseaseStatusID";

            using (var connection = new SqlConnection(_connectionString))
            {
                var command = new SqlCommand(queryString, connection);
                connection.Open();
                var dataReader = command.ExecuteReader();

                while (dataReader.Read())
                {
                    resultList.Add(GetSuggestionFromReader(dataReader));
                }
            }

            return resultList;

        }

        private Suggestion GetSuggestionFromReader(IDataReader reader)
        {
            var newSuggestion = new Suggestion
            {
                SuggestionID = reader["SuggestionID"] != null ? Convert.ToInt32(reader["SuggestionID"]) : 0,
                Description = reader["Description"]?.ToString() ?? string.Empty,
                Disease = new Disease
                {
                    Status = reader["DiseaseID"] != null ? (DiseasesStatusEnum) Enum.ToObject(typeof(DiseasesStatusEnum), Convert.ToInt32(reader["DiseaseID"])) : DiseasesStatusEnum.Normal,
                    Name = reader["DiseaseDescription"]?.ToString() ?? string.Empty
                },
                Parameter = new Parameter
                {
                    ParameterID = reader["ParameterID"] != null ? Convert.ToInt32(reader["ParameterID"]) : 0,
                    Description = reader["ParameterDescription"]?.ToString().Replace(" ", string.Empty) ?? string.Empty
                }
            };

            return newSuggestion;

        }
    }
}
