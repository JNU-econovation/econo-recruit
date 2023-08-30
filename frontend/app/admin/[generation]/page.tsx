import AdminSearch from "@/components/admin/Search.component";
import PageNavbarComponent from "@/components/common/PageNavbar.component";
import SortListComponent from "@/components/common/SortList.component";
import CommonNavbar from "@/components/common/navbar/Navbar.component";
import InterviewListComponent from "@/components/interview/List.component";
import { FC } from "react";

interface AdminPageProps {
  params: {
    generation: string;
  };
  searchParams: {
    type: string;
    order: string;
    page: string;
  };
}

const orderMenu = [
  { type: "newest", string: "최신순" },
  { type: "name", string: "이름순" },
];

const AdminPage: FC<AdminPageProps> = ({
  params,
  searchParams: { type = "", order = "", page = "1" },
}) => {
  const { generation } = params;

  return (
    <div className="px-24 w-max-[1280px] flex p-12">
      <CommonNavbar generation={generation} />
      <div className="flex-1 ml-32 min-w-[46rem] mb-48">
        <div className="flex w-full justify-end gap-8 my-12">
          <AdminSearch />
          <SortListComponent sortList={orderMenu} selected={order} />
        </div>
        <InterviewListComponent />
        <PageNavbarComponent
          maxLength={4}
          page={+page}
          url={`/admin/${generation}?type=${type}&order=${order}`}
        />
      </div>
    </div>
  );
};

export default AdminPage;
