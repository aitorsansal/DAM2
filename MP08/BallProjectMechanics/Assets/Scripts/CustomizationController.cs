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
    [BoxGroup("GameObjects")]
    [SerializeField] private GameObject colorPanelGO, texutrePanelGO, premadePanelGO, hatsPanelGO;
    [BoxGroup("Prefabs")]
    [AssetsOnly][SerializeField] private GameObject buttonPrefab;

    [SerializeField] 
    private StringColorDictionary colorDict;

    [SerializeField] private Texture2D text;

    [SerializeField] private GameObject renderedObject;
    private Renderer objRenderer;
    private GameObject currentPanel;
    
    
    
    private void Awake()
    {
        LoadColors();
        LoadPremades();
        objRenderer = renderedObject.GetComponent<Renderer>();
        currentPanel = colorPanelGO;
    }

    void LoadColors()
    {
        foreach (var color in colorDict.Keys)
        {
            var obj = Instantiate(buttonPrefab, colorPanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(colorDict[color]);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(colorDict[color]);});
        }
    }

    void LoadPremades()
    {
        var loaded = Resources.LoadAll("Premade");
        var shader = Resources.Load<Shader>("BallMaterialShader");
        foreach (var toLoad in loaded)
        {
            if (toLoad is not PremadeCombination combination) continue;
            var material = new Material(shader);
            material.SetColor("_Color", combination.color);
            material.SetTexture("_MainTex", combination.texture);
            var obj = Instantiate(buttonPrefab, premadePanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(material);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(material);});
        }
    }

    public void ChangeMaterial(Color col)
    {
        objRenderer.material.SetColor("_Color", col);
    }

    public void ChangeMaterial(Texture2D text)
    {
        objRenderer.material.SetTexture("_MainTex", text);
    }

    public void ChangeMaterial(Material mat)
    {
        objRenderer.material = mat;
    }

    public void ChangeCustomizationPanel(string panelName)
    {
        GameObject newActivePanel;
        currentPanel.SetActive(false);
        switch (panelName)
        {
            case "colorPanel":
                newActivePanel = colorPanelGO;
                break;
            case "texutrePanel":
                newActivePanel = texutrePanelGO;
                break;
            case "premadePanel":
                newActivePanel = premadePanelGO;
                break;
            case "hatsPanel":
                newActivePanel = hatsPanelGO;
                break;
            default:
                throw new NotImplementedException();
        }
        newActivePanel.SetActive(true);
        currentPanel = newActivePanel;
    }
    
    //TODO: things to add.
    // - list of colors and generate buttons acording to the colors
    // - button for custom color
    // - load textures from resources and generate buttons acording to texutres
    // - load scriptable objects from resources and generate buttons acording to textures
    // -- generate a material for each scriptable object and assing to object so it is seen
    // - load 3d ojects from resources and generate buttons acording to hats
    
}
