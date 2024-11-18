package py.com.mabit.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import py.com.mabit.servicios.ParametrosReporte;
import py.com.mabit.servicios.ReporteService;

@Controller
@RequestMapping("/reportes")
public class ReporteControlador {

	@Autowired
	private ReporteService reporteService;

	@GetMapping("/vista")
	public String mostrarVistaReporte(Model model) {
		model.addAttribute("parametrosReporte", new ParametrosReporte());
		return "reporte";
	}

	@GetMapping("/generar")
	public ResponseEntity<byte[]> generarReporte(@ModelAttribute ParametrosReporte parametrosReporte) {
		try {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("fechaInicio", parametrosReporte.getFechaInicio());
			parametros.put("fechaFin", parametrosReporte.getFechaFin());

//			byte[] reporte = reporteService.generarReporte(parametros, "usuarios", "xlsx");
			byte[] reporte = reporteService.generarReporte(parametros, "usuarios", "pdf");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			
			//headers.setContentDispositionFormData("inline", "reporte.pdf");//permite descargar directo
			headers.setContentDisposition(ContentDisposition.inline().filename("reporte.pdf").build());//abre en una nueva pestaña

			return new ResponseEntity<>(reporte, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
