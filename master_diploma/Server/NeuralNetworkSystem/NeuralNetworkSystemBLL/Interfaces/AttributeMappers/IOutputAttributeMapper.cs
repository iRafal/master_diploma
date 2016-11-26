using System.Reflection;
using NeuralNetworkSystemBLL.Interfaces.Components;

namespace NeuralNetworkSystemBLL.Interfaces.AttributeMappers
{
    public interface IOutputAttributeMapper
    {
        ILayer Map(PropertyInfo property, object obj, ILayer layer);
    }
}
