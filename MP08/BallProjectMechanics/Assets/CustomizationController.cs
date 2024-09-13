using System;
using System.Collections;
using System.Collections.Generic;
using Sirenix.OdinInspector;
using UnityEngine;
using UnityEngine.UI;

public class CustomizationController : MonoBehaviour
{
    [BoxGroup("Transforms")]
    [SerializeField] private Transform colorPanel, texutrePanel, premadePanel, hatsPanel;
    [BoxGroup("Prefabs")]
    [AssetsOnly][SerializeField] private GameObject buttonPrefab;

    [SerializeField] 
    private StringColorDictionary colorDict;

    [SerializeField] private Texture2D text;

    [SerializeField] private GameObject renderedObject;
    private Renderer objRenderer;
    
    
    
    private void Awake()
    {
        LoadColors();
        objRenderer = renderedObject.GetComponent<Renderer>();
    }

    void LoadColors()
    {
        foreach (var color in colorDict.Keys)
        {
            var obj = Instantiate(buttonPrefab, colorPanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(colorDict[color]);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(colorDict[color]);});
        }

        var shader = Resources.Load<Shader>("BallMaterialShader");
        if (shader is not null)
        {
            var material = new Material(shader);
            material.SetColor("_Color", Color.gray);
            material.SetTexture("_MainTex", text);
            var obj = Instantiate(buttonPrefab, colorPanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(material);
        }
    }

    public void ChangeMaterial(Color col)
    {
        objRenderer.material.SetColor("_Color", col);
    }

    public void ChangeMaterial(Texture2D tex)
    {
        objRenderer.material.SetTexture("_MainTex", tex);
    }

    public void ChangeMaterial(Material mat)
    {
        
    }
    
    //TODO: things to add.
    // - list of colors and generate buttons acording to the colors
    // - button for custom color
    // - load textures from resources and generate buttons acording to texutres
    // - load scriptable objects from resources and generate buttons acording to textures
    // -- generate a material for each scriptable object and assing to object so it is seen
    // - load 3d ojects from resources and generate buttons acording to hats
    
}
