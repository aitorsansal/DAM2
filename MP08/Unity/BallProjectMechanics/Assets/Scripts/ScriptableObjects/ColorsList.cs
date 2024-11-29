using System.Collections;
using System.Collections.Generic;
using Sirenix.OdinInspector;
using UnityEngine;
[CreateAssetMenu(menuName = "Customization/ColorsList")]
public class ColorsList : ScriptableObject
{
    [ListDrawerSettings(NumberOfItemsPerPage = 20)]
    public List<Color> colors;
}
