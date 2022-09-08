import convert.ConvertJSONToObject;
import model.Curso;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;

public class JSONToObjectApplication {

    public static void main(String[] args) {
        JSONObject jsonCurso = new JSONObject();

        JSONArray AlunosDoCurso = new JSONArray()
            .put(new JSONObject().put("id", "A").put("nome", "Paula"))
            .put(new JSONObject().put("id", 2L).put("nome", "Julia"));


        jsonCurso.put("id", 1L);
        jsonCurso.put("nome", "Ana Clara");
        jsonCurso.put("dataInicio", LocalDate.of(2020, 07, 06));
        jsonCurso.put("horaInicio", "14:12");
        jsonCurso.put("dataHoraFim", "2022-11-12 17:57:10");
        jsonCurso.put("alunos", AlunosDoCurso);

        Curso curso = ConvertJSONToObject.converter(jsonCurso, Curso.class);
        System.out.println(curso);
    }
}
