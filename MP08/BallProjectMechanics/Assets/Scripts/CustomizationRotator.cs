using System;
using System.Collections;
using System.Collections.Generic;
using DG.Tweening;
using UnityEngine;

public class CustomizationRotator : MonoBehaviour
{
    private void Awake()
    {
        transform.DORotate(new Vector3(45, 360, 0), 5, RotateMode.FastBeyond360).SetLoops(-1).SetEase(Ease.Linear);
    }
}
