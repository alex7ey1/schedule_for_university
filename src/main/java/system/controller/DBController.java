package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.Schedule;
import system.service.DBService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("/db")
public class DBController {
    @Autowired
    private DBService dbService;


    @RequestMapping(value = "/editPage", method = RequestMethod.GET)
    public ModelAndView getShow() {

        ArrayList<String> resltArray = dbService.getForTabOne();
        ArrayList<String> secondTabArr = dbService.getForTabTwo();
        ArrayList<String> thirdTabArr = dbService.getForTabThree();


        if (resltArray.isEmpty() && secondTabArr.isEmpty() && thirdTabArr.isEmpty()) {
            return new ModelAndView("error");
        } else {

            ModelAndView editPage = null;
//index out of bounds по индексам - с 0 до 19 , кол-во эл-ов 20
            ArrayList<String> firstTabLes = new ArrayList<>();
            for (int i = 0; i <= resltArray.size() - 1; i += 2) {
                firstTabLes.add(resltArray.get(i));
            }

            ArrayList<String> firstTabClass = new ArrayList<>();
            for (int i = 1; i <= resltArray.size(); i += 2) {
                firstTabClass.add(resltArray.get(i));
            }


            StringBuilder strb = new StringBuilder();
            ArrayList<String> secondTabFIO = new ArrayList<>();
            for (int i = 0, ii = 1, jj = 2; i <= secondTabArr.size() - 1; i += 4, ii += 4, jj += 4) {
                secondTabFIO.add(strb.append(secondTabArr.get(i)).append(" ").append(secondTabArr.get(ii)).append(" ").
                        append(secondTabArr.get(jj)).toString());
                strb.delete(0, strb.length());
            }

            ArrayList<String> secondTabLesson = new ArrayList<>();
            for (int p = 3; p <= secondTabArr.size(); p += 4) {
                secondTabLesson.add(secondTabArr.get(p));
            }

            ArrayList<String> thirdTabGroupNum = new ArrayList<>();
            for (int f = 0; f <= thirdTabArr.size() - 1; f += 2) {
                thirdTabGroupNum.add(thirdTabArr.get(f));
            }


            ArrayList<String> thirdTabLessonName = new ArrayList<>();
            for (int v = 1; v <= thirdTabArr.size(); v += 2) {
                thirdTabLessonName.add(thirdTabArr.get(v));
            }

            ArrayList<String> fourthTabGroupNum = dbService.getForTabFourGroup();
            ArrayList<String> fourthTabLesson = dbService.getForTabFourLesson();

            HashMap fourthTabClassroom = dbService.getForTabFourClassroom();
            ArrayList<Integer> fourthTabClass = new ArrayList(fourthTabClassroom.keySet());
            HashMap fourthTabTeach = dbService.getForTabFourTeacher();
            ArrayList<String> fourthTabTeacher = new ArrayList(fourthTabTeach.keySet());


            editPage = new ModelAndView("edit");
            editPage.addObject("firstTabLes", firstTabLes);
            editPage.addObject("firstTabClass", firstTabClass);
            editPage.addObject("secondTabFIO", secondTabFIO);
            editPage.addObject("secondTabLesson", secondTabLesson);
            editPage.addObject("thirdTabGroupNum", thirdTabGroupNum);
            editPage.addObject("thirdTabLessonName", thirdTabLessonName);
            editPage.addObject("fourthTabGroupNum", fourthTabGroupNum);
            editPage.addObject("fourthTabLesson", fourthTabLesson);
            editPage.addObject("fourthTabClass", fourthTabClass);
            editPage.addObject("fourthTabTeacher", fourthTabTeacher);

            return editPage;
        }
    }


    @RequestMapping(value = "/insSchedule", method = RequestMethod.GET)
    public ModelAndView insertRecord(@RequestParam("day_name") String day_name,
                                     @RequestParam("lesson_number") String lesson_number,
                                     @RequestParam("begin_time") String begin_time,
                                     @RequestParam("end_time") String end_time,
                                     @RequestParam("group_number") String group_number,
                                     @RequestParam("corpus_id") String corpus_id,
                                     @RequestParam("classroom_id") String classroom_id, //тут получили не id, а номер группы - получили key
                                     @RequestParam("lesson_name") String lesson_name,
                                     @RequestParam("lesson_type") String lesson_type,
                                     @RequestParam("teacher_id") String teacher_id,
                                     ModelMap model) {

//по key находим value (id) иниим этим value String corpus_id и оправляем в бд
        HashMap classroomMap = dbService.getForTabFourClassroom();
        Integer classroomID = (Integer) classroomMap.get(Integer.valueOf(classroom_id));
        classroom_id = classroomID.toString();
//валидация препода
        HashMap teacherMap = dbService.getForTabFourTeacher();
        teacher_id = String.valueOf(teacherMap.get(teacher_id));

        ModelAndView editRes = null;
        StringBuilder strb = new StringBuilder();

        strb.append("\'").append(day_name).append("\'").append(",").append("\'").append(lesson_number).append("\'").
                append(",").append("\'").append(begin_time).append("\'").append(",").append("\'").append(end_time).append("\'").
                append(",").append("\'").append(group_number).append("\'").append(",").append("\'").append(corpus_id).append("\'").
                append(",").append("\'").append(classroom_id).append("\'").append(",").append("\'").append(lesson_name).append("\'").
                append(",").append("\'").append(lesson_type).append("\'").append(",").append("\'").append(teacher_id).append("\'");

        int insRes = dbService.insertSchedule(strb.toString());

        editRes = getShow();
        model.addAttribute("insRes", insRes);
        editRes.setViewName("edit");
        return editRes;
    }


    @RequestMapping(value = "/displaySchedule", method = RequestMethod.GET)
    public ModelAndView dispSched(@RequestParam("group_num") String group_num,
                                  @RequestParam("day_name") String day_name,
                                  @ModelAttribute("root") Schedule schedule,
                                  ModelMap model) {
        ArrayList<String> scheduleArray = dbService.getSchedule(group_num, day_name);

        if (scheduleArray.isEmpty()) {
            return new ModelAndView("error");
        } else {

            StringBuilder builder;

            //День недели
            schedule.setDayName(scheduleArray.get(0));
            model.addAttribute("dayName", schedule.getDayName());

            //получаем номер группы
            schedule.setGroupNumber(scheduleArray.get(4));
            model.addAttribute("groupNumber", schedule.getGroupNumber());

            ModelAndView result = null;


            ArrayList<String> lesNum = new ArrayList<>();
            for (int i = 1; i <= scheduleArray.size(); i += 12) {
                lesNum.add(scheduleArray.get(i));
            }

            ArrayList<String> begTime = new ArrayList<>();
            for (int i = 2; i <= scheduleArray.size(); i += 12) {
                begTime.add(scheduleArray.get(i).substring(0, 5));
            }

            ArrayList<String> endTime = new ArrayList<>();
            for (int i = 3; i <= scheduleArray.size(); i += 12) {
                endTime.add(scheduleArray.get(i).substring(0, 5));
            }

            ArrayList<String> corpId = new ArrayList<>();
            for (int i = 5; i <= scheduleArray.size(); i += 12) {
                corpId.add(scheduleArray.get(i));
            }

            ArrayList<String> classrId = new ArrayList<>();
            for (int i = 6; i <= scheduleArray.size(); i += 12) {
                classrId.add(scheduleArray.get(i));
            }

            ArrayList<String> lesName = new ArrayList<>();
            for (int i = 7; i <= scheduleArray.size(); i += 12) {
                lesName.add(scheduleArray.get(i));
            }

            ArrayList<String> lesType = new ArrayList<>();
            for (int i = 8; i <= scheduleArray.size(); i += 12) {
                lesType.add(scheduleArray.get(i));
            }


            ArrayList<String> teacher = new ArrayList<>();
            builder = new StringBuilder();
            for (int i = 9, ii = 10, jj = 11; i <= scheduleArray.size(); i += 12, ii += 12, jj += 12) {
                teacher.add(builder.append(scheduleArray.get(i)).append(" ").append(scheduleArray.get(ii)).
                        append(" ").append(scheduleArray.get(jj)).toString());
                builder.delete(0, builder.length());
            }


            result = new ModelAndView("result");
            result.addObject("lesNum", lesNum);
            result.addObject("begTime", begTime);
            result.addObject("endTime", endTime);
            result.addObject("corpId", corpId);
            result.addObject("classrId", classrId);
            result.addObject("lesName", lesName);
            result.addObject("lesType", lesType);
            result.addObject("teacher", teacher);

            return result;

        }
    }



    /*@RequestMapping(value = "/selectFrom", method = RequestMethod.GET)
    public @ResponseBody
    String getFrom(String what, String from, String... optional) {
        return dbService.select(what, from, optional );
    }

    @RequestMapping(value = "/insertInto", method = RequestMethod.POST)
    public @ResponseBody
    void setStatement(String tableName, String values) {
        dbService.insert(tableName, values);
    }

    @RequestMapping(value = "/deleteFrom", method = RequestMethod.POST)
    public @ResponseBody
    void deleteFrom(String tableName, String conditions) {
        dbService.delete(tableName, conditions);
    }

    @RequestMapping(value = "/updateTable", method = RequestMethod.POST)
    public @ResponseBody
    void updateTable(String tableName, String conditions, String... setParam) {
        dbService.update(tableName, conditions, setParam);
    }*/
}
