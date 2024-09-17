using System.Collections;
using System.Collections.Generic;
using Sirenix.OdinInspector;
using UnityEngine;
[CreateAssetMenu(menuName = "Customization/TextureList")]
public class TextureList : ScriptableObject
{
    [ListDrawerSettings(NumberOfItemsPerPage = 20)]
    public List<Texture2D> textures;
}
