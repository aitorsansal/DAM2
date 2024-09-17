using System;
using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.Events;
using UnityEngine.Serialization;
using UnityEngine.UI;

public class CustomizationTilingSlider : MonoBehaviour
{
    [SerializeField] private TextMeshProUGUI tilingShow;
    private Slider slider;
    [SerializeField, TextArea] private string shownString;
    [SerializeField] private CustomizationController controller;
    
    
    
    [Serializable] private class CustomSliderEvent: UnityEvent<float>{}
    [Space]
    [SerializeField] private CustomSliderEvent sliderEvent = new CustomSliderEvent();
    
    private void Awake()
    {
        slider = GetComponent<Slider>();
        slider.onValueChanged.AddListener(OnSliderValueUpdate);
    }

    void OnSliderValueUpdate(float value)
    {
        tilingShow.text = string.Format(shownString, value);
        sliderEvent?.Invoke(value);
    }
}
