using System;
using System.Collections.Generic;
using System.Linq;
using Unity.VisualScripting;
using UnityEngine;

public abstract class UnitySerializedHashSet<TValue> : HashSet<TValue>, ISerializationCallbackReceiver
{
    [SerializeField, HideInInspector]
    private HashSet<TValue> valueData = new HashSet<TValue>();

    void ISerializationCallbackReceiver.OnAfterDeserialize()
    {
        this.Clear();
        foreach (var value in valueData)
        {
            Add(value);
        }
    }

    void ISerializationCallbackReceiver.OnBeforeSerialize()
    {
        var cur = new HashSet<TValue>(valueData);
        foreach (TValue item in this)
        {
            if(!cur.Contains(item))
                valueData.Add(item);
        }
    }
}

[Serializable] public class ColorHashSet : UnitySerializedHashSet<Color> {}