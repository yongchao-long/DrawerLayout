package com.example.drawerlayout;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        
        initView();
        initEvent();
    }
    
    public void OpenRightMenu(View view){
    	
    	mDrawerLayout.openDrawer(Gravity.RIGHT);
    	mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,Gravity.RIGHT);
    }

	private void initEvent() {
		
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int newState) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressLint("NewApi") @Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				
				Log.d("TAG", "slideOffset = "+slideOffset);
				
				float scale = 1 - slideOffset;
				float leftScale = 1.0f - 0.3f * scale;
				float rightScale = 0.8f + 0.2f * scale;
				
				if(drawerView.getTag().equals("LEFT")){
					
					mMenu.setScaleX(leftScale);
					mMenu.setScaleY(leftScale);
					mMenu.setAlpha(0.6f + 0.4f * (1 - scale));
					
					mContent.setPivotX(0);
					mContent.setPivotY(mContent.getMeasuredHeight() / 2);
					mContent.setTranslationX(mMenu.getMeasuredWidth() * (1 - scale));
					mContent.invalidate();
					mContent.setScaleX(rightScale);
					mContent.setScaleY(rightScale);
					
					
					
				}else{
					
					mContent.setTranslationX(-mMenu.getMeasuredWidth() * slideOffset);
					mContent.setPivotX(mContent.getMeasuredWidth());
					mContent.setPivotY(mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					mContent.setScaleX(rightScale);
					mContent.setScaleY(rightScale);
					
					
				}
				
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				
				
			}
			
			@Override
			public void onDrawerClosed(View drawerView) {
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);
				
			}
		});
		
	}

	private void initView() {
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.RIGHT);
		
	}


   
}
