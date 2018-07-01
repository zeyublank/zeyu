package com.dangzhongyang.yuekaoa.data.beans;

import java.util.List;

public class MusicBean {

    /**
     * body : {"result":[{"Children":[],"ClassFormat":2,"ID":5774,"IsFree":231,"Name":"解密\u201c独角兽\u201d","ParentID":0,"PartDuration":231,"PartSize":9,"Url":"https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/解密\u201c独角兽\u201d.mp3","WatchTime":0}]}
     * code : 200
     * message : Succes!
     */

    private BodyBean body;
    private int code;
    private String message;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class BodyBean {
        private List<ResultBean> result;

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * Children : []
             * ClassFormat : 2
             * ID : 5774
             * IsFree : 231
             * Name : 解密“独角兽”
             * ParentID : 0
             * PartDuration : 231
             * PartSize : 9
             * Url : https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/解密“独角兽”.mp3
             * WatchTime : 0
             */

            private int ClassFormat;
            private int ID;
            private int IsFree;
            private String Name;
            private int ParentID;
            private int PartDuration;
            private int PartSize;
            private String Url;
            private int WatchTime;
            private List<?> Children;

            public int getClassFormat() {
                return ClassFormat;
            }

            public void setClassFormat(int ClassFormat) {
                this.ClassFormat = ClassFormat;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public int getIsFree() {
                return IsFree;
            }

            public void setIsFree(int IsFree) {
                this.IsFree = IsFree;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getParentID() {
                return ParentID;
            }

            public void setParentID(int ParentID) {
                this.ParentID = ParentID;
            }

            public int getPartDuration() {
                return PartDuration;
            }

            public void setPartDuration(int PartDuration) {
                this.PartDuration = PartDuration;
            }

            public int getPartSize() {
                return PartSize;
            }

            public void setPartSize(int PartSize) {
                this.PartSize = PartSize;
            }

            public String getUrl() {
                return Url;
            }

            public void setUrl(String Url) {
                this.Url = Url;
            }

            public int getWatchTime() {
                return WatchTime;
            }

            public void setWatchTime(int WatchTime) {
                this.WatchTime = WatchTime;
            }

            public List<?> getChildren() {
                return Children;
            }

            public void setChildren(List<?> Children) {
                this.Children = Children;
            }
        }
    }
}
