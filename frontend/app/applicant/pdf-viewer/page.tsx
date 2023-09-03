import dynamic from "next/dynamic";

const ApplicationPdfViewerPage = () => {
  const ApplicantPdfViewr = dynamic(
    () => import("@/components/applicant/PdfViewr.component"),
    { ssr: false }
  );

  return <ApplicantPdfViewr />;
};

export default ApplicationPdfViewerPage;
