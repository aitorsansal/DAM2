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

    public void SetCustomization(Sprite img)
    {
        image.sprite = img;
    }

    public void SetCustomization(Material mat)
    {
        image.material = mat;
    }
}
