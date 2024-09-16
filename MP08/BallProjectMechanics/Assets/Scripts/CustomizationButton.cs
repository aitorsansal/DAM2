using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CustomizationButton : MonoBehaviour
{
    [SerializeField] private Image image;
    private CustomizationController controller;

    private void Awake()
    {
        controller = FindObjectOfType<CustomizationController>();
    }

    public void SetCustomization(Color col)
    {
        image.color = col;
    }

    public void SetCustomization(Texture2D text)
    {
        image.sprite = Sprite.Create(text, new Rect(0,0,text.width,text.height), 
                                            new Vector2(0.5f,0.5f));
    }

    public void SetCustomization(Material mat)
    {
        SetCustomization(mat.mainTexture as Texture2D);
        SetCustomization(mat.color);
    }
}
