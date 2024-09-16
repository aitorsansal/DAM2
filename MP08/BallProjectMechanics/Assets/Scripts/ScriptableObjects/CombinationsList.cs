using System.Collections;
using System.Collections.Generic;
using Sirenix.OdinInspector;
using UnityEngine;
[CreateAssetMenu(menuName = "Customization/CombinationLists")]
public class CombinationsList : ScriptableObject
{
    [ListDrawerSettings(NumberOfItemsPerPage = 20)]
    public List<PremadeCombination> combinations;
}
