package com.ljw.telecontrol.telecontrol;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ljw.controlservice.Commander;
import com.ljw.controlservice.ControlService;
import com.ljw.controlservice.HeartBeatServer;
import com.ljw.utils.CommonUtils;
import com.ljw.utils.Global;
import com.ljw.utils.MediaController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ControlService mService;
    Context mContext;
    Button bt_163,bt_next,bt_previous,bt_play,bt_pause,bt_volume_up,bt_volume_down,bt_mute,bt_bind;
    ListView lst_clients;
    ControlService cs = null;
    Handler handler = null;
    ArrayList<HeartBeatServer.Client> mClients = null;
    int curclientid = -1;
    String curip = "";
    ClientsAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        Global.setAppContext(mContext);
        bt_163 = (Button) findViewById(R.id.bt_163);
        bt_play = (Button) findViewById(R.id.bt_play);
        bt_pause = (Button) findViewById(R.id.bt_pause);
        bt_next = (Button) findViewById(R.id.bt_next);
        bt_previous = (Button) findViewById(R.id.bt_previous);
        bt_volume_down = (Button) findViewById(R.id.bt_volume_down);
        bt_volume_up = (Button) findViewById(R.id.bt_volume_up);
        bt_mute = (Button) findViewById(R.id.bt_mute);
        bt_bind = (Button) findViewById(R.id.bt_bind);
        lst_clients = (ListView) findViewById(R.id.lst_clients);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Commander.WHAT_HEARTBEAT:
                        if(adapter != null)
                            adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;

                }
                super.handleMessage(msg);
            }
        };
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bt_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ControlService.class);
                MainActivity.this.bindService(i,conn,Context.BIND_AUTO_CREATE);
            }
        });

        bt_163.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //CommonUtils.startApp(mContext, "com.netease.cloudmusic");
                //MediaController.PlayPause(mContext);
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip,Commander.CMD_OPEN163);
            }
        });

        bt_play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_PLAY);
            }
        });
        bt_pause.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_PAUSE);
            }
        });
        bt_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_NEXT);
            }
        });
        bt_previous.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_PREVIOUS);
            }
        });
        bt_volume_down.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_VOLUME_DOWN);
            }
        });
        bt_volume_up.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_VOLUME_UP);
            }
        });
        bt_mute.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if( cs != null && curclientid != -1)
                    cs.sendCmd(curip, Commander.CMD_MEDIA_VOLUME_MUTE);
            }
        });
    }


    private ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            cs = ((ControlService.ControlBinder) service).getService();
            cs.setHandler(handler);
            mClients = cs.getClients();
            adapter = new ClientsAdapter(mContext,mClients);
            lst_clients.setAdapter(adapter);

        }

        /** 无法获取到服务对象时的操作 */
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            cs = null;
        }

    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_music) {

        } else if (id == R.id.nav_settting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ClientsAdapter extends BaseAdapter implements View.OnClickListener {
        private ArrayList<HeartBeatServer.Client> mClients;
        private Context mContext;
        private LayoutInflater inflater;
        public ClientsAdapter(Context ctxt,ArrayList<HeartBeatServer.Client> clients) {
            mContext = ctxt;
            inflater = LayoutInflater.from(ctxt);
            mClients = clients;
        }
        @Override
        public int getCount() {
            return mClients.size();
        }

        @Override
        public Object getItem(int position) {
            return mClients.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            HeartBeatServer.Client cl = mClients.get(position);
            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.client_adapter, null);
                viewHolder.user = (TextView) convertView.findViewById(R.id.txt_user);
                viewHolder.ip = (TextView) convertView.findViewById(R.id.txt_ip);
                viewHolder.lastping = (TextView) convertView.findViewById(R.id.txt_lastping);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.user.setText(cl.username);
            viewHolder.ip.setText(cl.ip);
            SimpleDateFormat myFmt1=new SimpleDateFormat("yy-MM-dd HH:mm");
            viewHolder.lastping.setText(myFmt1.format(cl.lastping));
            viewHolder.id = position;
            convertView.setOnClickListener(this);
            return convertView;
        }
        class ViewHolder {
            TextView user;
            TextView ip;
            TextView lastping;
            int id;
        }
        @Override
        public void onClick(View v) {
            ViewHolder viewHolder = (ViewHolder) v.getTag();
            curclientid = viewHolder.id;
            curip = mClients.get(curclientid).ip;
        }
    }

}
