using System;
using System.Collections.Generic;
using DG.Tweening;
using UnityEngine;

public class ScrollRectAutoHide : MonoBehaviour
{
    [SerializeField] private CanvasGroup scrollBar;
    private Tween alphaChange;

    private void Awake()
    {
        OnUpdate();
    }

    public void OnUpdate()
    {
        if (transform.localScale.magnitude < .4f) return;
        scrollBar.alpha = 1;
        if (alphaChange is not null) alphaChange.Kill();
        alphaChange = scrollBar.DOFade(.2f, 2.5f).SetEase(Ease.Linear).SetRecyclable(true);
    }
}
