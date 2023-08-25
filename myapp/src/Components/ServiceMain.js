import Breadcrumb from "./Breadcrumb";

export default function ServiceMain () {

    return (
        <div>
            <form action="http://localhost:8080/" method="post" encType="multipart/form-data">
                업로드할 파일 : <input type="file" name="upload[]" multiple /><br/>
                <input type="submit" />
            </form>
        </div>        
    );
}