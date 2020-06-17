import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotaPraticaDetailComponent } from 'app/entities/nota-pratica/nota-pratica-detail.component';
import { NotaPratica } from 'app/shared/model/nota-pratica.model';

describe('Component Tests', () => {
  describe('NotaPratica Management Detail Component', () => {
    let comp: NotaPraticaDetailComponent;
    let fixture: ComponentFixture<NotaPraticaDetailComponent>;
    const route = ({ data: of({ notaPratica: new NotaPratica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaPraticaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NotaPraticaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotaPraticaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notaPratica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notaPratica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
