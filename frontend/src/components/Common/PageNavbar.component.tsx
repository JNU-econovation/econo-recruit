const PageNavbarComponent = ({
  page,
  url,
  maxLength,
}: {
  page: number;
  url: string;
  maxLength: number;
}) => (
  <div className="flex w-full justify-end gap-2 absolute bottom-12 right-24">
    {Array.from({ length: maxLength }).map((_, i) => (
      <a
        href={url + `&page=${i + 1}`}
        className={i + 1 === page ? 'p-3' : 'text-[#B6B6B6] p-3'}
        key={i}
      >
        {i + 1}
      </a>
    ))}
  </div>
);

export default PageNavbarComponent;
