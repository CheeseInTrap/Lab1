package form;

import java.io.File;
import java.io.IOException;

import control.ShowDirectedGraphControl;

public class ShowDirectedGraphForm {
	public ShowDirectedGraphForm(String DotStr,File file)
	{
        ShowDirectedGraphControl.showDirectedGraph(DotStr, "DotGraph");
        try {
        	ShowDirectedGraphControl.showImage(file.getParent() + "\\DotGraph.jpg");
        } catch (IOException e) {
          e.printStackTrace();
        }
	}
}
