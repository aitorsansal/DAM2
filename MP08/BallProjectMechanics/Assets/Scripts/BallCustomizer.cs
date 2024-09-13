using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BallCustomizer : MonoBehaviour
{
    public Material mat;
    public Color col;
    private static readonly int Color1 = Shader.PropertyToID("_Color");

    private void Update()
    {
        mat.SetColor(Color1, col);
    }
}
