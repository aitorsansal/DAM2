using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class CustomizationButton : MonoBehaviour
{
    [SerializeField] private Image image;

    public void SetCustomization(Color col)
    {
        image.color = col;
    }

    public void SetCustomization(Texture2D text)
    {
        image.sprite = Sprite.Create(text, new Rect(0,0,text.width,text.height), 
                                            new Vector2(0.5f,0.5f));
    }

    public void SetCustomization(Material mat, bool directMaterial = false)
    {
        if (directMaterial)
            image.material = mat;
        else
        {
            SetCustomization(mat.mainTexture as Texture2D);
            SetCustomization(mat.color);
        }
    }

    public void SetCustomization(Sprite sprite)
    {
        image.sprite = sprite;
    }
}
