using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using DG.Tweening;
using Sirenix.OdinInspector;
using UnityEngine;
using UnityEngine.Serialization;
using UnityEngine.UI;
using Object = UnityEngine.Object;

public class CustomizationController : MonoBehaviour
{
    private static readonly int Color1 = Shader.PropertyToID("_Color");
    private static readonly int MainTex = Shader.PropertyToID("_MainTex");
    private static readonly int Tiling = Shader.PropertyToID("_Tiling");

    [BoxGroup("Transforms")]
    [SerializeField] private Transform colorPanel, texturePanel, premadePanel, hatsPanel;

    [BoxGroup("GameObjects")]
    [SerializeField] private GameObject colorPanelGO, texutrePanelGO, premadePanelGO, hatsPanelGO;
    [BoxGroup("Prefabs")]
    [AssetsOnly][SerializeField] private GameObject buttonPrefab;

    private List<Color> colors;
    private List<Texture2D> textures;
    private List<PremadeCombination> premadeCombinations;


    public Action<float> changeSlider;
    [SerializeField] private ColorPicker colorPicker;
    [SerializeField] private RectTransform colorPickerGO;
    [Space]
    [SerializeField] private GameObject renderedObject;
    [SerializeField] private Slider tilingSlider;
    
    private Renderer objRenderer;
    private GameObject currentPanel;
    
    
    
    private void Awake()
    {
        colors = Resources.Load<ColorsList>("Colors").colors;
        textures = Resources.Load<TextureList>("Textures").textures;
        premadeCombinations = Resources.Load<CombinationsList>("Combinations").combinations;
        print(colorPickerGO.anchoredPosition);
        LoadColors();
        LoadPremades();
        LoadTextures();
        objRenderer = renderedObject.GetComponent<Renderer>();
        currentPanel = colorPanelGO;
        colorPicker.onColorChanged += ChangeMaterial;
        CloseColorWheel();
    }

    void LoadColors()
    {
        foreach (var color in colors.OrderBy(x => -x.r))
        {
            var obj = Instantiate(buttonPrefab, colorPanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(color);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(color);});
        }

        var wheelPicker = Instantiate(buttonPrefab, colorPanel);
        wheelPicker.GetComponent<CustomizationButton>().SetCustomization(Resources.Load<Sprite>("RainbowTexture"));
        wheelPicker.GetComponent<Button>().onClick.AddListener(OpenColorWheel);
    }

    void LoadPremades()
    {
        var shader = Resources.Load<Shader>("BallMaterialShader");
        
        foreach (var combination in premadeCombinations)
        {
            var material = new Material(shader);
            material.SetColor(Color1, combination.color);
            material.SetTexture(MainTex, combination.texture);
            if(!Mathf.Approximately(combination.tiling, 1f)) material.SetFloat(Tiling, combination.tiling);
            var obj = Instantiate(buttonPrefab, premadePanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(material);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(material);});
        }
    }

    void LoadTextures()
    {
        foreach (var texture in textures.Where(x => x is not null))
        {
            var obj = Instantiate(buttonPrefab, texturePanel);
            obj.GetComponent<CustomizationButton>().SetCustomization(texture);
            obj.GetComponent<Button>().onClick.AddListener(delegate{ChangeMaterial(texture);});
        }
    }

    public void ChangeMaterial(Color col)
    {
        objRenderer.material.SetColor(Color1, col);
    }

    public void ChangeMaterial(Texture2D texture)
    {
        objRenderer.material.SetTexture(MainTex, texture);
    }

    public void ChangeMaterial(Material mat)
    {
        objRenderer.material = mat;
    }

    public void ChangeTiling(Slider slider)
    {
        objRenderer.material.SetFloat(Tiling, slider.value);
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
            case "texturePanel":
                newActivePanel = texutrePanelGO;
                tilingSlider.value = objRenderer.material.GetFloat(Tiling);
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
    
    public void ChangeRenderObject(string obj)
    {
        GameObject newMesh = obj switch
        {
            "sphere" => GameObject.CreatePrimitive(PrimitiveType.Sphere),
            "cube" => GameObject.CreatePrimitive(PrimitiveType.Cube),
            "capsule" => GameObject.CreatePrimitive(PrimitiveType.Capsule),
            _ => throw new NotImplementedException()
        };
        
        renderedObject.GetComponent<MeshFilter>().mesh = newMesh.GetComponent<MeshFilter>().mesh;
        Destroy(newMesh);
    }

    void OpenColorWheel()
    {
        colorPickerGO.DOAnchorPos(new Vector2(0, 0), .6f).SetEase(Ease.Linear);
        colorPickerGO.DOScale(1, .6f).SetEase(Ease.Linear);

    }

    public void CloseColorWheel()
    {
        colorPickerGO.DOAnchorPos(new Vector2(0.00f, -445.00f), .6f).SetEase(Ease.Linear);
        colorPickerGO.DOScale(0, .6f).SetEase(Ease.Linear);
    }
    
    
    //TODO: things to add.
    // - load 3d ojects from resources and generate buttons acording to hats
    
}
