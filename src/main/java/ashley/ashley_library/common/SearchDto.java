package ashley.ashley_library.common;


public class SearchDto {

    private int page; //현재 페이지 번호
    private  int recordSize; //페이지당 출력할 데이터 개수
    private  int pageSize; //화면 하단에 출력할 페이지 사이즈
    private String keyword; //검색키워드
    private  String researchType; //검색유형


    public SearchDto(int page, int recordSize, int pageSize) {
        this.page = page;
        this.recordSize = recordSize;
        this.pageSize = pageSize;
    }

    public int getOffSet(){
        return (page-1) * recordSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getResearchType() {
        return researchType;
    }

    public void setResearchType(String researchType) {
        this.researchType = researchType;
    }
}
