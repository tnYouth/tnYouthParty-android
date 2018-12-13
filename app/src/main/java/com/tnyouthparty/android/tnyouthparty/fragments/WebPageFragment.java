package com.tnyouthparty.android.tnyouthparty.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tnyouthparty.android.tnyouthparty.R;

public class WebPageFragment extends Fragment {

    private WebPageViewModel mViewModel;
    private WebView webViewSite;
    private String url = "http://www.tnYouthParty.com";
    private String currentURL = "";

    public WebPageFragment() {

    }

    @SuppressLint("ValidFragment")
    public WebPageFragment(String url) {
        this.url = url;
    }

    public static WebPageFragment newInstance() {
        return new WebPageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_page_fragment, container, false);
        this.webViewSite = view.findViewById(R.id.webview_site);
        return view;
    }

    public void init() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WebPageViewModel.class);
        webViewSite.getSettings().setJavaScriptEnabled(true);
        webViewSite.loadUrl(!currentURL.isEmpty() ? currentURL : url);
        webViewSite.setWebViewClient(new CustomWebViewClient());
        // TODO: Use the ViewModel
    }

    private class CustomWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            currentURL = url;
            view.loadUrl(url);
            return true;
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            currentURL = url;
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    public WebView getWebView() {
        return this.webViewSite;
    }

}
