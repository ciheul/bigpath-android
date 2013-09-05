package com.ciheul.bigpath;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.ciheul.bigpath.R;

public class MainActivity extends Activity {

    private MapView myOpenMapView;
    private MapController myMapController;

    private ItemizedOverlay<OverlayItem> itemizedOverlay;

    private ResourceProxy resourceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

        myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        myMapController = myOpenMapView.getController();
        myMapController.setZoom(14);

        double lon = 107.62207;
        double lat = -6.91552;

        IGeoPoint point = new GeoPoint(lat, lon); // lat lon and not inverse
        myMapController.setCenter(point);

        ArrayList<OverlayItem> listMarker = new ArrayList<OverlayItem>();
        listMarker.add(new OverlayItem("Bandung", "Description", new GeoPoint(lat, lon)));

        itemizedOverlay = new ItemizedIconOverlay<OverlayItem>(listMarker,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "single tap", Toast.LENGTH_LONG).show();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "long press", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }, resourceProxy);

        myOpenMapView.getOverlays().add(itemizedOverlay);
        myOpenMapView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
